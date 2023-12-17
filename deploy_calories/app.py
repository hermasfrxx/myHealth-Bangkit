from fastapi import FastAPI
from pydantic import BaseModel
from keras.models import load_model
import numpy as np

app = FastAPI()

class ModelInput(BaseModel):
    Sex: int
    Age: int
    Height: float
    Weight: float

# Load the model directly
calories = load_model('model.h5')

@app.post('/calories_prediction')
def calories_pred(input_parameters: ModelInput):
    sex = input_parameters.Sex
    age = input_parameters.Age
    hei = input_parameters.Height
    wei = input_parameters.Weight

    # Ensure input has the correct shape
    input_data = np.array([[sex, age, hei, wei]])

    prediction = calories.predict(input_data)

    predicted_class = np.argmax(prediction, axis=1)

    if predicted_class[0] == 0:
        return 'low calories'
    elif predicted_class[0] == 1:
        return 'Medium calories'
    elif predicted_class[0] == 2:
        return 'high calories'
    else:
        return 'out of context'
