#!/usr/bin/env python
import matplotlib.pyplot as plt
from csv import reader
import sys

# read csv file as a list of lists
csvFileName = sys.argv[1]
rows = []
with open(csvFileName, 'r') as read_obj:
    # pass the file object to reader() to get the reader object
    csv_reader = reader(read_obj, delimiter=',')
    # Pass reader object to list() to get a list of lists
    rows = list(csv_reader)

legends = []
for i in range(1, len(rows[0])):
    legends.append(rows[0][i])

numberOfElements = []
insertionSortExecutionTime = []
executionTimes = []

print(executionTimes)
for i in range(1, len(rows)):
    numberOfElements.append(float(rows[i][0]))

for x in range(1, len(rows[0])):
    executionTimes.append([])
    for i in range(1, len(rows)):
        executionTimes[x - 1].append(float(rows[i][x]))

i = 0
testName = 'test'
print(legends[0])

for result in executionTimes:
    testName = 'C:\\Users\\root\\Desktop\\kthedu\\Y2\\AoD\\Sorting\\LabJ\\src\\main\\resources\\scripts\\output\\'
    plt.ticklabel_format(style='plain')
    plt.plot(numberOfElements, result)
    plt.grid(True)
    plt.title(legends[i])
    plt.ylabel('time (T)')
    plt.xlabel('number of elements (N)')
    testName += sys.argv[2] + '-' + legends[i]
    testName += '.png'
    print(testName)
    plt.savefig(testName,  bbox_inches='tight')
    plt.show()
    i += 1

plt.ticklabel_format(style='plain')

for result in executionTimes:
    plt.plot(numberOfElements, result)

plt.grid(True)
plt.legend(legends)
plt.ylabel('time (T)')
plt.xlabel('number of elements (N)')
plt.savefig('C:\\Users\\root\\Desktop\\kthedu\\Y2\\AoD\\Sorting\\LabJ\\src\\main\\resources\\scripts\\output\\' + sys.argv[2], bbox_inches='tight')
plt.show()
