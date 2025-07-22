-- Drop tables in reverse order to avoid foreign key conflicts?
DROP TABLE IF EXISTS prenatal_visit;
DROP TABLE IF EXISTS healthcare_provider;
DROP TABLE IF EXISTS pregnancy_lab;
DROP TABLE IF EXISTS symphysis_fondus;
DROP TABLE IF EXISTS medical_history;
DROP TABLE IF EXISTS pregnancy;
DROP TABLE IF EXISTS pregnancy_history;
DROP TABLE IF EXISTS partner;
DROP TABLE IF EXISTS patient;

-- Create Patient Table
CREATE TABLE patient (
    Patient_ID BIGSERIAL PRIMARY KEY,
    First_Name VARCHAR(100) NOT NULL,
    Last_Name VARCHAR(100) NOT NULL,
    Date_Of_Birth DATE NOT NULL,
    National_ID BIGINT UNIQUE NOT NULL,
    Address TEXT,
    Phone_Number VARCHAR(15),
    Marital_Status VARCHAR(50),
    Education_Level VARCHAR(50),
    Working BOOLEAN,
    Occupation VARCHAR(100),
    Work_Percentage DECIMAL,
    Country_Of_Origin VARCHAR(100),
    Language VARCHAR(100),
    Interpreter_Needed BOOLEAN
);

-- Create Partner Table
CREATE TABLE partner (
    Partner_ID BIGSERIAL PRIMARY KEY,
    Patient_ID BIGINT REFERENCES Patient(Patient_ID) ON DELETE CASCADE,
    First_Name VARCHAR(100) NOT NULL,
    Last_Name VARCHAR(100) NOT NULL,
    Date_Of_Birth DATE NOT NULL,
    National_ID BIGINT UNIQUE NOT NULL,
    Address TEXT,
    Phone_Number VARCHAR(15),
    Occupation VARCHAR(100),
    Country_Of_Origin VARCHAR(100),
    Language VARCHAR(100)
);

--  Create PregnancyHistory Table
CREATE TABLE pregnancy_history (
    History_ID BIGSERIAL PRIMARY KEY,
    Patient_ID INTEGER REFERENCES Patient(Patient_ID) ON DELETE CASCADE,
    Number_Of_Pregnancies INTEGER,
    Spont_Abortions INTEGER,
    Liveborn INTEGER,
    ExUteral INTEGER,
    Stillborn INTEGER,
    Notes TEXT
);



-- Create Pregnancy Table
CREATE TABLE pregnancy (
                           Pregnancy_ID BIGSERIAL PRIMARY KEY,
                           Patient_ID INTEGER REFERENCES Patient(Patient_ID) ON DELETE CASCADE,
                           Last_Mens DATE NOT NULL,
                           Estimated_Duedate DATE NOT NULL,
                           Ultrasound_Duedate DATE NOT NULL,
                           Corrected_Duedate DATE NOT NULL,
                           Fetal_Diagnostic BOOLEAN DEFAULT FALSE,
                           Assisted_Conception BOOLEAN DEFAULT FALSE,
                           Assist_Concept_Date DATE,
                           Multiple_Fetus BOOLEAN DEFAULT FALSE,
                           Prepregnant_Height INTEGER,
                           Prepregnant_Weight INTEGER,
                           Prepregnant_BMI FLOAT,
                           Breastfeed_Consult BOOLEAN,
                           Prenatal_Consult BOOLEAN,
                           Coparent_Declaration BOOLEAN,
                           Maternity_Ward VARCHAR(100),
                           Ward_Phone VARCHAR(15),
                           Healthstation VARCHAR(100),
                           HS_Address VARCHAR(100),
                           HS_Phone VARCHAR(15),
                           Notes TEXT
);

-- Create MedicalHistory Table
CREATE TABLE medical_history (
    History_ID BIGSERIAL PRIMARY KEY,
    -- changed from initial patientID reference in case of multiple pregnancies?
    Pregnancy_ID BIGINT NOT NULL UNIQUE REFERENCES pregnancy(pregnancy_id) ON DELETE CASCADE,

	
	Chronic_Diseases TEXT,
    Genetic_Conditions TEXT,
    Smoking_Use VARCHAR(50),
    Snus_Use VARCHAR(50),
    Alcohol_Use VARCHAR(50),
    Other_DrugUse VARCHAR(50),
	
    Smoking_Week1 VARCHAR(50),
    Snus_Week1 VARCHAR(50),
    Alcohol_Week1 VARCHAR(50),
	
    Smoking_Week36 VARCHAR(50),
    Snus_Week36 VARCHAR(50),
    Alcohol_Week36 VARCHAR(50),
	
    Medications TEXT,
    Med_List TEXT,
    Drug_Allergy BOOLEAN,
    Folate VARCHAR(50),
    Notes TEXT
);

-- Create SymphysisFondus Table
CREATE TABLE symphysis_fondus (
    Symphysis_Fondus_ID BIGSERIAL PRIMARY KEY,
    Pregnancy_ID INTEGER REFERENCES Pregnancy(Pregnancy_ID) ON DELETE CASCADE,
    Patient_ID INTEGER REFERENCES Patient(Patient_ID) ON DELETE CASCADE,
    Week INTEGER,
    Uteral_Size INTEGER
);

-- Table: PregnancyLab
CREATE TABLE pregnancy_lab (
    Pregnancy_Lab_ID BIGSERIAL PRIMARY KEY,
    Pregnancy_ID INTEGER REFERENCES Pregnancy(Pregnancy_ID) ON DELETE CASCADE,
    Patient_ID INTEGER REFERENCES Patient(Patient_ID) ON DELETE CASCADE,
    Hb INTEGER,
    Sferritin INTEGER,
    HepB_HBsAg BOOLEAN,
    HepB_antiHBc BOOLEAN,
    HIV BOOLEAN,
    Syphilis BOOLEAN,
    ABO_Rh VARCHAR(10),
    Blood_Antigens TEXT,
    Fetus_Rh_DWeek24 BOOLEAN,
    RhD_Prophylactic_Week28 BOOLEAN,
    Fetus_RhD_Consent VARCHAR(50),
    Fetus_RhD_Date TIMESTAMP,
    Fetus_RhD_Notes TEXT,
    Chlamydia BOOLEAN,
    Toxoplasmosis BOOLEAN,
    Rubella_Antigen BOOLEAN,
    HepC BOOLEAN,
    MRSA_VRE_ESBL BOOLEAN,
    HbA1c DECIMAL(5, 2),
    Glucose_Test_Fasting DECIMAL(5, 2),
    Glucose_Test_2Hours DECIMAL(5, 2),
    Glucose_Test_Date TIMESTAMP
);

-- Create HealthcareProvider Table
CREATE TABLE healthcare_provider (
    Provider_ID BIGSERIAL PRIMARY KEY,
    First_Name VARCHAR(100) NOT NULL,
    Last_Name VARCHAR(100) NOT NULL,
    Role VARCHAR(100),
    Phone_Number VARCHAR(15),
    Email VARCHAR(100) UNIQUE NOT NULL,
    Facility_Name VARCHAR(100)
);

-- Create PrenatalVisit Table
CREATE TABLE prenatal_visit (
    Visit_ID BIGSERIAL PRIMARY KEY,
    Pregnancy_ID INTEGER REFERENCES Pregnancy(Pregnancy_ID) ON DELETE CASCADE,
    Visit_Date TIMESTAMP NOT NULL,
    Pregnancy_Week INTEGER NOT NULL,
    Weight INTEGER,
    Blood_Pressure VARCHAR(20),
    Urine_Protein VARCHAR(50),
    Edema INTEGER,
    Fetal_Position VARCHAR(50),
    Fetal_Movement BOOLEAN,
    Fetal_Heart_Rate INTEGER,
    Presence BOOLEAN,
    Medications TEXT,
    Work_Percentage DECIMAL(5, 2),
    Notes TEXT,
    Healthcare_Provider_ID INTEGER REFERENCES Healthcare_Provider(Provider_ID) ON DELETE SET NULL
);