import json
import requests

url = 'http://127.0.0.1:8000/calories_prediction'

input_data_for_model ={
    'Sex': 0,  # Assuming 0 represents male (adjust based on your encoding)
    'Age': 12,
    'Height': 0,
    'Weight': 0
}

input_json = json.dumps(input_data_for_model)

response = requests.get(url, data=input_json)

print(response.text)