#!/usr/bin/python

# This program takes an input of a file and outputs whether or not
# the list of numbers contained in the file is sorted
#
# It is assumed the numbers in the file are in the format: [1, 2, 3]
#
# Usage: ./check.py <filename>
import sys

name = sys.argv[1]
with open(name) as fileobj:
    for line in fileobj:
        arr = line.split(',')

        # Get rid of brackets
        arr[0] = arr[0].replace('[', '')
        arr[len(arr) - 1] = arr[len(arr) - 1].replace(']', '')

        # check ordering
        num = 0
        for i in range(len(arr)):
            curr = int(arr[i])
            if num > curr:
                print "List is not sorted"
                exit(1)
            else:
                num = curr
        print "List is sorted"
        exit(0)