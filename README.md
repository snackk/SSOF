# SSOF
Basic usage:
	make clean && make && make run
	
makefile:
	makefile is already running all the sqli and xss tests.
	this could be changed by editing the makefile.
	
flags:
	-xss -> runs the code on xss vunerability.
	-sqli -> runs the code on sqli vunerability.
	-ansiColor=true -> if set to true will display colors on the output, default is set to false, must be passed on the beginning.