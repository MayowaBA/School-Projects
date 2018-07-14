#------------------------------------------------------------------------------
#  Makefile for CMPS 101 pa3
#  Creates an executable jar file called Sparse.  This makefile will compile 
#  all .java files in your current working directory.  
#------------------------------------------------------------------------------

MAINCLASS  = Sparse
JAVAC      = javac 
JAVASRC    = $(wildcard *.java)
SOURCES    = $(JAVASRC) Makefile README
CLASSES    = $(patsubst %.java, %.class, $(JAVASRC))
JARCLASSES = $(patsubst %.class, %*.class, $(CLASSES))
JARFILE    = $(MAINCLASS) 
FILES   = List.java Matrix.java ListTest.java MatrixTest.java Sparse.java README Makefile

all: $(JARFILE)
	
$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(JARCLASSES)
	chmod +x $(JARFILE)
	rm Manifest

%.class: %.java
	$(JAVAC) $<

clean:
	rm -f *.class $(JARFILE)
	
submit : $(FILES)
	submit  cmps101-pt.s18  pa3 $(FILES)
	
check:
	ls  /afs/cats.ucsc.edu/class/cmps101-pt.s18/pa3/mborisad

