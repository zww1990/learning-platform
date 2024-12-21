import requests
import json
from os.path import expanduser
from requests.auth import HTTPBasicAuth

def login():
    with open(expanduser('brain_credentials.txt')) as f:
        credentials = json.load(f)
    username, password = credentials
    session = requests.session()
    session.auth = HTTPBasicAuth(username, password)
    response = session.post('https://api.worldquantbrain.com/authentication')

    print(response.status_code)
    print(response.json())

if __name__ == '__main__':
    login()
