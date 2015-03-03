#!/usr/bin/python
#
# This program generates a list of numbers that quicksort will have a
# hard time with
#
# Usage: ./killer.py <range> 
#

import random
import sys

if(len(sys.argv) < 2):
    print "Usage: numgen.py <range> [<repeats>]"
    exit(1)

num = int(sys.argv[1])

l = [0] * num 
k = num / 2

for i in range(1, k+1): 
  if i % 2 == 1:
    l[i - 1] = i
    l[i] = k + i
  l[k + i - 1] = 2 * i

print l
