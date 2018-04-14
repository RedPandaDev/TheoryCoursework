# TO RUN:
# Navigate to folder w/ .txt file and .py script
# INPUT:
# 	user$ python
# 	>>>import dfaTester
# 	>>>dfaTester.dfaComplement("FILENAME.txt")
#import re
def dfaComplement(p):
	#List var to store each line individually
	outList = []
	#Read the input file
	userIn = open(p, mode='r')
	outList = userIn.read().split("\n")
	print("==============================================================")
	print("TEST 1: ")
	print("Data read in: ")
	print(outList)
	print()
	print("Data type: ")
	print(type(outList))
	print("==============================================================")
	
	userIn.close()

		#Append line values
	totStates = outList[0]
	states = outList[1]
	alphaSize = outList[2]
	alphabet = outList[3]
	transitions = outList[4:-3]
	#Always last element
	acceptState = outList[-1]
	totAccept = outList[-2]
	startState = outList[-3]
	#^ Always the same place in list
	#v Always variable length
	# for a in range(4, len(outList)-3):
	#         transitions.append(a)


	# Debug print statements
	print("==============================================================")
	print("TEST 2: ")
	print('Total states ', totStates)
	print()
	print('Possible states ')
	print(states)
	print()
	print('Alphabet size ', alphaSize)
	print()
	print('alphabet ')
	print(alphabet)
	print()
	print('Total accept states: ', totAccept)
	print()
	print('Accept states: ', acceptState)
	print('Start states: ', startState)
	print()
	print("List transitions: ")
	for t in transitions:
		print(t)
	print("==============================================================")
# ===========================TASK CODE===========================
	# Task 1 code:

for p in states:
	if states[p] == acceptState:
		states.pop(p)
	print(len(states))











