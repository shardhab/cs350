#!/usr/bin/python
#
# This program generates a list of random numbers
#
# Usage: ./numgen.py <range> [<repeats>]
# where 'range' is the range of numbers to use in the array (ex: 1-101)
# and repeats is the number of times to repeat the numbers
#
# For example ./numgen.py 4 2 will output a shuffled list of: [1, 2, 3, 1, 2, 3]

import random
import sys

if(len(sys.argv) < 2):
    print "Usage: numgen.py <range> [<repeats>]"
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
