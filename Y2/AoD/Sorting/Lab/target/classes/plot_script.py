import matplotlib.pyplot as plt
import numpy as np
import csv

from csv import reader

# read csv file as a list of lists

rows = []
with open('the-file-name.csv', 'r') as read_obj:
    # pass the file object to reader() to get the reader object
    csv_reader = reader(read_obj, delimiter=';')
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
    testName = 'test'
    plt.ticklabel_format(style='plain')
    plt.plot(np.array(numberOfElements), np.array(result))
    plt.grid(True)
    plt.title(legends[i])
    plt.ylabel('time (T)')
    plt.xlabel('number of elements (N)')
    testName += str(i)
    testName += '.png'
    print(testName)
    plt.savefig(testName + '.png',  bbox_inches='tight')
    plt.show()
    i += 1

plt.ticklabel_format(style='plain')

for result in executionTimes:
    plt.plot(np.array(numberOfElements), np.array(result))

plt.grid(True)
plt.legend(legends)
plt.ylabel('time (T)')
plt.xlabel('number of elements (N)')
plt.savefig('test.png', bbox_inches='tight')
plt.show()
