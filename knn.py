import csv
import random
import math
import operator
 
def loadDataset(filename, filename1, trainingSet=[] , testSet=[]):
	missCount=0
	indexX=[]
	indexY=[]
	with open(filename, 'rb') as csvfile:
	    lines = csv.reader(csvfile)
	    dataset = list(lines)
	    for x in range(len(dataset)):
	        for y in range(14):
				
	            dataset[x][y] = float(dataset[x][y])
	        trainingSet.append(dataset[x])
	
	
	with open(filename1, 'rb') as csvfile:
	    lines = csv.reader(csvfile)
	    dataset = list(lines)
	    for x in range(len(dataset)):
	        for y in range(14):
				if (dataset[x][y]=='?'):
					indexX.append(x)
					indexY.append(y)
					dataset[x][y]=2000
					missCount=missCount+1
				dataset[x][y] = float(dataset[x][y])
		testSet.append(dataset[x])	
				
           			
	#print ("Number of missing data ="+str(missCount)  )   
	
	
	f2=open('missCount.txt','w+')
	f2.write(str(missCount))
 

def euclideanDistance(instance1, instance2, length):
	distance = 0
	for x in range(length):
		if(instance1[x]<1000):
			distance += pow((instance1[x] - instance2[x]), 2)
	return math.sqrt(distance)
 
def getNeighbors(trainingSet, testInstance, k):
	distances = []
	length = len(testInstance)-1
	for x in range(len(trainingSet)):
		dist = euclideanDistance(testInstance, trainingSet[x], length)
		distances.append((trainingSet[x], dist))
	distances.sort(key=operator.itemgetter(1))
	neighbors = []
	for x in range(k):
		neighbors.append(distances[x][0])
	#print neighbors
	return neighbors
 
def getResponse(neighbors,y):
	classVotes = {}
	for x in range(len(neighbors)):
		response = neighbors[x][y]
		if response in classVotes:
			classVotes[response] += 1
		else:
			classVotes[response] = 1
	sortedVotes = sorted(classVotes.iteritems(), key=operator.itemgetter(1), reverse=True)
	#print sortedVotes[0][0]
	return sortedVotes[0][0]
 
def getAccuracy(trainingSet, predictions):
	correct = 0
	for x in range(len(trainingSet)):
		for y in range(14):
			if trainingSet[x][y] == predictions[x]:
				correct += 1
	return (correct/float(len(testSet))) * 100.0
	
def main():
	# prepare data
	trainingSet=[]
	testSet=[]
	f1=open('missVals.txt', 'w+')
	loadDataset('trainingSet.txt','SwissData.txt', trainingSet, testSet)
	#print 'Train set: ' + repr(len(trainingSet))
	#print 'Test set: ' + repr(len(testSet))
	# generate predictions
	predictions=[]
	k = 3
	for x in range(len(testSet)):
		for y in range(14):
			if(testSet[x][y]>1000):
				neighbors = getNeighbors(trainingSet, testSet[x], k)
				result = getResponse(neighbors,y)
				f1.write(str(result)+" ")             
				predictions.append(result)
				#print('> predicted=' + repr(result) + ', actual=' + repr(trainingSet[x][y]))
	#accuracy = getAccuracy(trainingSet, predictions)
	#print('Accuracy: ' + repr(accuracy) + '%')
	
main()