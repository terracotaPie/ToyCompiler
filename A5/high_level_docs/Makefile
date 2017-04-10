all: docs.pdf

docs.pdf: docs.css docs.org
	pandoc -f org+emoji -t html --toc --css=docs.css -o docs.html docs.org
