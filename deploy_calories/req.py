import json
import requests

url = 'http://127.0.0.1:8000/calories_prediction'

input_data_for_model ={
    'Sex': 1,
    'Age': 20,
    'Height':170.0, 
    'Weight': 75.0
}

input_json = json.dumps(input_data_for_model)

response = requests.post(url, data=input_json)

print(response.text)