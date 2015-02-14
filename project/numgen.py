#!/usr/bin/python
import random
import sys

if(len(sys.argv) < 2):
    print "Usage: numgen <range> [<repeats>]"
    exit(1)

num = int(sys.argv[1])
if(len(sys.argv) == 3):
    double = int(sys.argv[2])
else:
    double = 1

l = []

for j in range(1, double + 1):
    for i in range(1, num):
        l.append(i)

random.shuffle(l)
print l
