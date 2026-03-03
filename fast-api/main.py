from fastapi import FastAPI
import yfinance as yf
import pandas as pd
from sklearn.linear_model import LinearRegression
import numpy as np

app = FastAPI()

@app.get("/predict")
def predict(symbol: str):
    data = yf.download(symbol, period="6mo")
    data = data[['Close']]
    data['Days'] = np.arange(len(data))

    X = data[['Days']]
    y = data['Close']

    model = LinearRegression()
    model.fit(X, y)

    next_day = np.array([[len(data)]])
    predicted_price = model.predict(next_day)[0]

    return {"symbol": symbol, "predicted_price": float(predicted_price)}