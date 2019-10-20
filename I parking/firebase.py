
import time



import pyrebase

config = {
    "apiKey": "AIzaSyCKfgmNcqOUB5m4Utegwexqy7C-u7DRgm8",
    "authDomain": "parking-ee898.firebaseapp.com",
    "databaseURL": "https://parking-ee898.firebaseio.com",
    "storageBucket": "parking-ee898.appspot.com"
    # "serviceAccount": "path/to/serviceAccountCredentials.json"
}

while(1):
    firebase = pyrebase.initialize_app(config)
    db = firebase.database()
    users = db.child("val/status").get()
    print(users.val())
    time.sleep(5)

