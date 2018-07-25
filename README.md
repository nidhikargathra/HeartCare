# HeartCare
An intelligent system to predict probability of heart diseases by using classification algorithms

PROBLEM DEFINITION:

The user enters his/her health details (age, fasting blood sugar, ECG, blood pressure, etc.) as the input. 
The output produced by the system is the probability of the user having a heart disease. 
Additional health tips also need to be provided based on the diagnosis.

The programming tools required for the project are:

•	JDK
•	Python 2.7.x
•	POSTGRES SQL

Dataset: https://archive.ics.uci.edu/ml/datasets/Heart+Disease

The attributes of the dataset include:

1. age: age
2. gender: 0:male; 1:female
3. ch_pain: angina type
4. rest_bp: resting blood pressure (in mm/Hg)
5. choles: cholstrol in mili
6. bs_f: blood sugar(fasting
7. rest_ecg: resting electrocardiographic
8. thal_ach: max rate of the heart
9. ex_ang: angina induced due to exercise(one: yes; zero: no)
10. old_peak = ST decrease induced due to exercise
11. slope: (one:upward slope; two:no slope; three: downward slope)
12. ca: number of major artery
13. thale: 3 = normal; 6 = fixed defect; 7 = reversible defect
14. number: result of the system
     0: less than 50% narrowing in major artery radius
     1: greater than 50% narrowing in major artery radius


Purpose:

This project aims at creating a system that can accurately predict the presence of a heart disease. 
The targeted audiences for the system would be men above the age of 45 and women above the age of 50. 
Despite the general age assumption, the system will also prove to be useful for other users who may face heart problems at a much younger age.  
The system serves as a check up for the patient that he can do from the comfort of his or her house. 

Project Implementation:

Initially the data obtained consists of missing values and outliers. Hence, pre-processing of data is required before we can use it for prediction. 

The steps in pre-processing are:

a.	Computing missing values using knn algorithm

b.	Smoothing data using binning algorithm

After pre-processing we can use this data for prediction. Prediction was implemented using 2 approaches.

     a. ID3 (Decision tree approach)
     
     b. Naive Bayesian(Probabilistic approach)
     
After, comparing both approaches it was found that ID3 had a better performance measure than Naive Bayes. 
Also, ID3 does not assume class independence.
