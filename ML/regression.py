# -*- coding: utf-8 -*-
"""Regression.ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/1BBeGeACoZfHF90-OeZkhYyLGN-uqp0eH
"""

import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
from sklearn.linear_model import LinearRegression
from sklearn.preprocessing import LabelEncoder
from sklearn.model_selection import train_test_split
from sklearn import metrics
import seaborn as sns
import statsmodels.formula.api as smf

df = pd.read_csv("https://raw.githubusercontent.com/pvannyamelia/bangkit_2021_capstone_Rangkayo/main/ML/violence_data.csv")

df.dropna(inplace=True)

df.drop(columns=['RecordID', 'Survey Year'], inplace=True)
df.columns = ['country', 'gender', 'dmg_question', 'dmg_response', 'question', 'value']

dupl = list(df[df.duplicated()==True].index)
df.drop(df.index[dupl], inplace=True)

df.head()

le = LabelEncoder()
for i in ['country', 'gender', 'dmg_question', 'dmg_response', 'question']:
  df[i] = le.fit_transform(df[i])
df.head()

corr = df.corr()
sns.heatmap(corr, vmin=-1, vmax=1, cmap = 'coolwarm', annot=True, fmt='.2f', linewidths=0.1)
plt.title("Correlation Between Variables", pad=20)

df.drop(columns=['country', 'dmg_question', 'dmg_response'], inplace=True)

X = df.iloc[:, :-1]
y = df.iloc[:, -1]

X_train, X_test, y_train, y_test = train_test_split(X, y, random_state=42)
lr_re = LinearRegression()
lr_re.fit(X_train, y_train)
print("Intercept: ", lr_re.intercept_)
print("Coefficient: ", lr_re.coef_)

lm_re = smf.ols(formula='value~gender+question', data=df).fit()
print('Summary: ', lm_re.summary())

y_pred_re = lr_re.predict(X_test)

print('Model Score: ', lr_re.score(X_test, y_test))
print("RMSE number is: ", np.sqrt(metrics.mean_squared_error(y_test, y_pred_re)))

while True:
    jk = input ("\nWhat is your gender: F) Female. M) Male. [M/F]? : ")
    if jk in ['M', 'F']:
      jk=le.fit_transform([jk])
      break
    else:
      print("\nPlease only enter M/F, it is sensitive case")

while True:
    question = input ("\nWhich one of these statements do you most agree?\n1. A husband is justified in hitting or beating his wife for at least one specific reason.\n2. A husband is justified in hitting or beating his wife if she argues with him.\n3. A husband is justified in hitting or beating his wife if she burns the food\n4. A husband is justified in hitting or beating his wife if she goes out without telling him\n5. A husband is justified in hitting or beating his wife if she neglects the children.\n6. A husband is justified in hitting or beating his wife if she refuses to have sex with him.\nPlease answer with the number of the question [1 / 2 / 3 / 4 / 5 / 6] :")
    if question in ['1', '2', '3', '4', '5', '6']:
      question = int(question)
      break
    else:
      print("\nPlease only enter 1 / 2 / 3 / 4 / 5 / 6")

pertanyaan = [jk, question]
probability = lr_re.predict([pertanyaan])[0]
print("\nKemungkinan terjadi KDRT: {:2f}%".format(lr_re.predict([pertanyaan])[0]))

if probability <= 30:
  print("Kemungkinan terjadi rendah")
elif probability > 30 and probability <= 60:
  print("Kemungkinan terjadi sedang")
elif probability > 60:
  print("Kemungkinan terjadi tinggi")
else:
  print("Hayolo, ada yang tidak beres :)")