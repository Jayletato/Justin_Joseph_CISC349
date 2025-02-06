# import main Flask class and request object
from flask import Flask, request

# create the Flask app
app = Flask(__name__)

@app.route('/query-example')
def query_example():
    # if key doesn't exist, returns None
    language = request.args.get('language')

    # if key doesn't exist, returns a 400, bad request error
    framework = request.args['framework']

    # if key doesn't exist, returns None
    website = request.args.get('website')

    return '''
              <h1>The language value is: {}</h1>
              <h1>The framework value is: {}</h1>
              <h1>The website value is: {}'''.format(language, framework, website)

@app.route('/form-example', methods=['GET','POST'])
def form_example():
    if request.method == 'POST':
        language = request.form.get('language')
        framework = request.form.get('framework')
        return 'POST'
    return 'GET'

@app.route('/json-example', methods=['POST'])
def json_example():
    request_data = request.get_json()
    username = request_data['username']
    password = request_data['password']

    # Do something to validate user and password
    return '{"authorized" : true}'

if __name__ == '__main__':
    # run app in debug mode on port 5000
    app.run(host='0.0.0.0', debug=True, port=5000)