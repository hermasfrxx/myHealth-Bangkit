from fastapi import FastAPI
from pydantic import BaseModel
import MySQLdb
from keras.models import load_model
import numpy as np

db_config ={
    'host':'localhost',
    'user': 'root',
    'passwd':'',
    'db':'food_data'
}
conn = MySQLdb.connect(**db_config)

app = FastAPI()

class ModelInput(BaseModel):
    Sex: int
    Age: int
    Height: float
    Weight: float

# class Calori(BaseModel):
#     Food: str
#     Grams: int
#     Calories: int
#     Protein:int
#     Fat: int
#     Id_Calories= int

# Load the model directly
calories = load_model('model.h5')

@app.get('/calories_prediction')
def calories_pred(input_parameters: ModelInput):
    sex = input_parameters.Sex
    age = input_parameters.Age
    hei = input_parameters.Height
    wei = input_parameters.Weight

    # Ensure input has the correct shape
    input_data = np.array([[sex, age, hei, wei]])

    # Get the raw predictions (probabilities for each class)
    raw_predictions = calories.predict(input_data)

    # Get the index of the class with the highest probability
    predicted_class = np.argmax(raw_predictions, axis=1)[0]
    with conn.cursor() as cursor:
        cursor.execute("SELECT * FROM foodi WHERE Id_Calories=%s",(int(predicted_class),))
        result = cursor.fetchall()
    if result:
        # Convert the result to a list of dictionaries for better JSON serialization
        food_data_list = [
            {'Id': row[0],'Food': row[1],'Grams':row[2],'Calories':row[3],'Protein':row[4],'Fat':row[5]}  # Replace column1, column2, etc. with actual column names
            for row in result
        ]
        return food_data_list
    else:
        return {'message': 'No food data found.'}

