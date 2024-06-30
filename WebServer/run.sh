#! /bin/bash

# Set environment variables
export FLASK_APP=app.py
export FLASK_ENV=production
# Run the Flask application with Gunicorn
waitress-serve --listen=192.168.1.15:8000 app:app