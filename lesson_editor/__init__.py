import os
import re
"""
from flask import (
	Flask, render_template, request
)
"""

path = 'app/src/main/res/values/arrays.xml'
lessonre = re.compile(r'<array name="l(.*)"(.*)</array>', re.S)
lessonsre = re.compile(r'<array name="lessons">(.*)</array>', re.S)
questionre = re.compile(r'<array name"q(.*)"(.*)</array>', re.S)
itemre = re.compile(r'<item>(.*)</item>')

"""
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
	os.makedirs(app.instance_path)
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
"""
f = open(path)
text = f.read()
f.close()
lessons_raw = list(map(lambda m: m.group(0), re.finditer(lessonsre, text)))
print(text)
lessons = {}
print(lessons_raw)
for n, lesson in lessons_raw:
    l = re.findall(itemre, lesson)
    name = l.pop(0)
    lessons[int(n)] = name, l

print(lessons)
