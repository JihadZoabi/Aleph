import os

import re

from flask import (
	Flask, render_template, request
)

path = 'Aleph/app/src/main/res/values/arrays.xml'

lessonre = re.compile(r"<array name=\"l(.*)\"(.*)</array>")
lessonsre = re.compile(r"<array name=\"lessons\">(.*)</array>")
questionre = re.compile(r"<array name=\"q(.*)\"(.*)</array>")

def create_app(test_config=None):
	app = Flask(__name__, instance_relative_config=True)
	app.config.from_mapping(
		SECRET_KEY='dev',
		DATABASE=os.path.join(app.instance_path,
			'lesson_editor.sqlite'),
	)
	if test_config is None:
		app.config.from_pyfile('config.py', silent=True)
	else:
		app.config.from_mapping(test_config)
	try:
		os.makedirs(app.instance_path)
	except OSError:
		pass;
	@app.route('/', methods=('GET', 'POST'))
	def hello():
		if request.method == 'POST':
			pass
		return render_template('page.html') 
	@app.route('/questions')
	def makeq():
		return render_template('makeq.html')
	@app.route('/lessons', methods=('GET', 'POST'))
	def makel():
		pass
	return app

f = os.open(path)
text = f.read()

def func():
	f = os.open(path)
	text = f.read()
	f.close()
	lessons_raw = re.findall(lessonsre, text)
	lessons = {}
	for n, lesson in lessons_raw:
		lessons[int(n)] = lesson
	
