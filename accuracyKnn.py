import csv
import random
import math
import operator
 
def loadDataset(filename, filename1, trainingSet=[] , testSet=[]):
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
				
				dataset[x][y] = float(dataset[x][y])
		testSet.append(dataset[x])	

def getAccuracy(trainingSet,testSet):
	correct = 0
	correct1=0
	tots1=0
	tots2=0
	deviation=[]
	list(deviation)
	#print len(testSet)
	for x in range(len(trainingSet)):
		for y in range(14):
			if trainingSet[x][y] == testSet[x][y]:
				correct += 1
			if(trainingSet[x][y]<5):
				#print(str(trainingSet[x][y])+"  "+str(testSet[x][y]))
				tots1 +=1
				if trainingSet[x][y] == testSet[x][y]:
					correct1 += 1
			
				
			if(trainingSet[x][y]>=5):
				
				tots2 +=1
				deviation.append(((abs(float(trainingSet[x][y]) - float(testSet[x][y])))/(trainingSet[x][y]))*100)
	sum1=0.0
	sum1=sum(deviation)
	p=0
	f3=open('missCount.txt')
	missingvalueCount=int(f3.readline())
	f3.close()
	file4=open('statistics.txt','w+')
	file4.write('Total number of training data entries:'+str(len(trainingSet))+'\n')
	file4.write( 'Total number of Data entries:'+str(len(testSet))+'\n')
	file4.write('Total number of values'+str(len(testSet*14))+'\n')
	file4.write('Total number of missing values:'+str(missingvalueCount)+'\n')
	for i in range(len(deviation)):
		if(deviation[i]!=0):
			p=p+1
	avg=float(sum1)/p
	
	#print 'Number of fuzzy missing values:'+str(p)
	file4.write('Number of fuzzy missing values:'+str(p)+'\n')
	
	j=missingvalueCount-p
	file4.write('Number of fixed missing values:'+str(j)+'\n')
	#print 'Number of fixed missing values:'+str(j)
	
	file4.write("Fuzzy value Error:"+str(avg)+'%'+"\n")
	#print "Fuzzy value Error:"+str(avg)+'%'
	l=(len(testSet)*14-missingvalueCount)
	
	g=(((tots1-float(correct1)))/j)*100
	file4.write("Fixed value Error:"+str(g)+'%'+"\n")
	#print "Fixed value Error:"+str(g)+'%'
	
	file4.write('Accuracy after Cleaning: ' +str(100.0-float(g+avg)/2)+'%'+'\n')
	
	file4.write('Overall Data Accuracy: ' + repr(((correct)/(float(len(testSet))*14)) * 100.0) + '%')
	file4.close()
	
	#print l
	return ((correct)/(float(len(testSet))*14)) * 100.0
	#print(correct-l)
	#return ((correct-l))/(float(missingvalueCount)) * 100.0



def main():
	# prepare data
	trainingSet=[]
	testSet=[]
	
	loadDataset('trainingSet.txt','missingvalue.txt', trainingSet, testSet)
	
	#print len(trainingSet)
	#print len(testSet)
	accuracy = getAccuracy(trainingSet, testSet)
	#print('Overall Data Accuracy: ' + repr(accuracy) + '%')
	
	
main()