-- Test data script? 

-- 1. Insert Patient
INSERT INTO patient (
    First_Name, Last_Name, Date_Of_Birth, National_ID, Address, Phone_Number,
    Marital_Status, Education_Level, Working, Occupation, Work_Percentage,
    Country_Of_Origin, Language, Interpreter_Needed
) VALUES (
    'Jane', 'Doe', '1995-10-04', 123456789, '123 Main St', '+4712345678',
    'Single', 'University', TRUE, 'Engineer', 100.0,
    'Norway', 'Norwegian', FALSE
);

-- 2. Insert Partner
INSERT INTO partner (
    Patient_ID, First_Name, Last_Name, Date_Of_Birth, National_ID, Address,
    Phone_Number, Occupation, Country_Of_Origin, Language
) VALUES (
    1, 'John', 'Doe', '1999-07-21', 987654321, '123 Main St',
    '+4798765432', 'Teacher', 'Norway', 'Norwegian'
);

-- 3. Insert PregnancyHistory
INSERT INTO pregnancy_history (
    Patient_ID, Number_Of_Pregnancies, Spont_Abortions, Liveborn, ExUteral, Stillborn, Notes
) VALUES (
    1, 2, 0, 1, 0, 0, 'First pregnancy normal, second ongoing.'
);

-- 4. Insert Pregnancy
INSERT INTO pregnancy (
    Patient_ID, Last_Mens, Estimated_Duedate, Ultrasound_Duedate, Corrected_Duedate,
    Fetal_Diagnostic, Assisted_Conception, Assist_Concept_Date, Multiple_Fetus,
    Prepregnant_Height, Prepregnant_Weight, Prepregnant_BMI,
    Breastfeed_Consult, Prenatal_Consult, Coparent_Declaration,
    Maternity_Ward, Ward_Phone, Healthstation, HS_Address, HS_Phone, Notes
) VALUES (
    1, '2025-01-01', '2025-10-08', '2025-10-08', '2025-10-08',
    FALSE, FALSE, NULL, FALSE,
    165, 65, 23.9,
    TRUE, TRUE, TRUE,
    'Oslo Hospital', '+4712345678', 'Local Healthstation', 'Health St 10', '+4799998888', 'Routine pregnancy.'
);

-- 5. Insert MedicalHistory
INSERT INTO medical_history (
    Pregnancy_ID, Chronic_Diseases, Genetic_Conditions, Smoking_Use, Snus_Use,
    Alcohol_Use, Other_DrugUse,
    Smoking_Week1, Snus_Week1, Alcohol_Week1,
    Smoking_Week36, Snus_Week36, Alcohol_Week36,
    Medications, Med_List, Drug_Allergy, Folate, Notes
) VALUES (
    1, 'Diabetes', 'None', 'Occasional', 'No',
    'Yes', 'None',
    'Yes', 'No', 'Occasionally',
    'No', 'No', 'No',
    'Paracetamol', 'Paracetamol 500mg', FALSE, 'Yes', 'Patient has mild seasonal allergies.'
);

-- 6. Insert Symphysis Fondus
INSERT INTO symphysis_fondus (
    Pregnancy_ID, Patient_ID, Week, Uteral_Size
) VALUES (
    1, 1, 20, 22
);

-- 7. Insert Pregnancy Lab
INSERT INTO pregnancy_lab (
    Pregnancy_ID, Patient_ID,
    Hb, Sferritin, HepB_HBsAg, HepB_antiHBc, HIV, Syphilis,
    ABO_Rh, Blood_Antigens,
    Fetus_Rh_DWeek24, RhD_Prophylactic_Week28, Fetus_RhD_Consent,
    Fetus_RhD_Date, Fetus_RhD_Notes,
    Chlamydia, Toxoplasmosis, Rubella_Antigen, HepC, MRSA_VRE_ESBL,
    HbA1c, Glucose_Test_Fasting, Glucose_Test_2Hours, Glucose_Test_Date
) VALUES (
    1, 1,
    12, 35, TRUE, FALSE, FALSE, FALSE,
    'A+', 'Anti-D',
    TRUE, TRUE, 'Consented',
    '2025-06-01', 'No abnormalities.',
    FALSE, FALSE, TRUE, FALSE, FALSE,
    5.4, 4.5, 7.8, '2025-05-01'
);

-- 8. Insert Healthcare Provider
INSERT INTO healthcare_provider (
    First_Name, Last_Name, Role, Phone_Number, Email, Facility_Name
) VALUES (
    'Sara', 'Midwife', 'Midwife', '+4711122233', 'sara.midwife@oslohospital.no', 'Oslo Hospital'
);

-- 9. Insert Prenatal Visit
INSERT INTO prenatal_visit (
    Pregnancy_ID, Visit_Date, Pregnancy_Week, Weight, Blood_Pressure, Urine_Protein,
    Edema, Fetal_Position, Fetal_Movement, Fetal_Heart_Rate, Presence,
    Medications, Work_Percentage, Notes, Healthcare_Provider_ID
) VALUES (
    1, '2025-04-01', 12, 68, '120/80', 'Negative',
    0, 'Cephalic', TRUE, 140, TRUE,
    NULL, 80.0, 'Routine checkup.', 1
);
