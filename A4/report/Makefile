RUN:= $(shell command -v latexrun 2> /dev/null)
LMK:= $(shell command -v latexmk 2> /dev/null)
PTX:= $(shell command -v pdflatex 2> /dev/null)


all: report.pdf

# .PHONY: FORCE
# report.pdf: FORCE 
# ifdef RUN  
# 	latexrun report.tex # use latexmk or pdflatex if you dont have latexrun
# else
# 	ifdef LMK
# 			latexmk report.tex 
# 	else
# 			pdflatex report.tex
# 	endif
# endif

report.pdf: report.tex content/*.tex 
ifndef DOT
		pdflatex report.tex
else
    latexrun report.tex 
endif

.PHONY: clean
clean:
	latexrun --clean-all && rm -rf *.out 
