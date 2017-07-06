"""
THIS PYTHON SCRIPT CONTAINS MATRICES THAT CAN BE USED
TO SIMULATE CONVOLUTION FUNCTIONS IN PYTHON

"""
import random

class mat5x5():

    x =  [       # 5X5 image of an "X"
            [1,0,0,0,1],
            [0,1,0,1,0],
            [0,0,1,0,0],
            [0,1,0,1,0],
            [1,0,0,0,1],
            ]
    o =  [       # 5X5 image of an "O"
            [0,1,1,1,0],
            [1,0,0,0,1],
            [1,0,0,0,1],
            [1,0,0,0,1],
            [0,1,1,1,0]
            ]

    slash = [   # 5 x 5 image of a slash
            [20,1,0,0,0],
            [1,20,1,0,0],
            [0,1,20,1,0],
            [0,0,1,20,1],
            [0,0,0,1,20]
    ]
class mat10x10():
    x = [       # 10X10 image of an "x"
            [1,1,0,0,0,0,0,0,1,1],
            [0,1,1,0,0,0,0,1,1,0],
            [0,0,1,1,0,0,1,1,0,0],
            [0,0,0,1,1,1,1,0,0,0],
            [0,0,0,0,1,1,0,0,0,0],
            [0,0,0,0,1,1,0,0,0,0],
            [0,0,0,1,1,1,1,0,0,0],
            [0,0,1,1,0,0,1,1,0,0],
            [0,1,1,0,0,0,0,1,1,0],
            [1,1,0,0,0,0,0,0,1,1]
    ]

    slash = [       # 10 x 10 image of a slash
            [1,1,0,0,0,0,0,0,0,0],
            [1,1,1,0,0,0,0,0,0,0],
            [0,1,1,1,0,0,0,0,0,0],
            [0,0,1,1,1,0,0,0,0,0],
            [0,0,0,1,1,1,0,0,0,0],
            [0,0,0,0,1,1,1,0,0,0],
            [0,0,0,0,0,1,1,1,0,0],
            [0,0,0,0,0,0,1,1,1,0],
            [0,0,0,0,0,0,0,1,1,1],
            [0,0,0,0,0,0,0,0,1,1]
    ]



class filters():

    image3x3 = [       # 3X3 filter
            [1,0,1],
            [0,1,0],
            [1,0,1],
            ]


class values:

    # Creates a 10x10 random 2Dmatrix
    def return10x10(min,max):
        matrix = []     # Matrix
        row = []        # Row
        for row_ in range(15):       # For each of the 10 rows:
            for cell in range(15):  # For each of the 10 numbers in in row:
                row.append(random.randint(min,max)) # Add a random number
            matrix.append(row)      # Add row to matrix
            row = []                # Clear row
        return matrix               # Return random matrix
