#------------------------------------------------------------------------------
# Mayowa Borisade
# mborisad
# CMPS 12M
# 02/25/18
# 
# Makefile for List ADT
#------------------------------------------------------------------------------

# Variables
JARFILE    = ListClient
MAINCLASS  = ListClient
SOURCES    = List.java ListInterface.java ListTest.java ListClient.java\
             ListIndexOutOfBoundsException.java
CLASSES    = List.class ListInterface.class ListClient.class List\$$Node.class\
             ListIndexOutOfBoundsException.class
             
SUBMIT     = submit cmps012b-pt.w18 lab6 Makefile

# Build Targets
all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(SOURCES)
	javac -Xlint $(SOURCES)  

clean:
	rm -f $(CLASSES) $(JARFILE)
	
submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)
        
check:
	ls  /afs/cats.ucsc.edu/class/cmps012b-pt.w18/lab6/mborisad
