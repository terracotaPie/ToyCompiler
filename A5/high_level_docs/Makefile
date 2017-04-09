all: docs.html

docs.html: docs.css docs.org
	pandoc -f org+emoji -t html --toc --css=docs.css -o docs.html docs.org
