import requests
import json
from os.path import expanduser
from requests.auth import HTTPBasicAuth
from time import sleep

def login():
    with open(expanduser('brain_credentials.txt')) as f:
        credentials = json.load(f)
    username, password = credentials
    session = requests.session()
    session.auth = HTTPBasicAuth(username, password)
    response = session.post('https://api.worldquantbrain.com/authentication')

    # print(response.status_code)
    print(response.json())
    return session

def simulate(session):
    simulation_data = {
        'type': 'REGULAR',
        'settings': {
            'instrumentType': 'EQUITY',
            'region': 'USA',
            'universe': 'TOP3000',
            'delay': 1,
            'decay': 0,
            'neutralization': 'INDUSTRY',
            'truncation': 0.08,
            'pasteurization': 'ON',
            'unitHandling': 'VERIFY',
            'nanHandling': 'OFF',
            'language': 'FASTEXPR',
            'visualization': False
        },
        'regular': 'liabilities/assets'
    }
    params = json.dumps(simulation_data)
    # print(params)
    headers = { 'Content-Type': 'application/json' }
    response = session.post('https://api.worldquantbrain.com/simulations', data=params, headers=headers)
    # print(response.status_code)
    sim_progress_url = response.headers['Location']
    print(sim_progress_url)

    while True:
        sim_progress_resp = session.get(sim_progress_url)
        retry_after_sec = float(sim_progress_resp.headers.get('Retry-After', 0))
        if retry_after_sec == 0:
            break
        sleep(retry_after_sec)
    alpha_id = sim_progress_resp.json()['alpha']
    print(alpha_id)

if __name__ == '__main__':
    simulate(login())
