"""
THIS IS A COLLECTION OF FUNCTIONS THAT CAN BE USED
AS A PART OF THE CONVOLUTIONAL LAYER IN
ANY CONVOLUTIONAL NEURAL NETWORK.

Code by Laurence, otherwise noted
"""

# Matrix multipliction from https://stackoverflow.com/questions/10508021/matrix-multiplication-in-python
def matrixmult (A, B):
    rows_A = len(A)
    cols_A = len(A[0])
    rows_B = len(B)
    cols_B = len(B[0])

    if cols_A != rows_B:
      print("Cannot multiply the two matrices. Incorrect dimensions.")
      return

    # Create the result matrix
    # Dimensions would be rows_A x cols_B
    C = [[0 for row in range(cols_B)] for col in range(rows_A)]

    for i in range(rows_A):
        for j in range(cols_B):
            for k in range(cols_A):
                C[i][j] += A[i][k] * B[k][j]
    return C

# This function prints a 1D matrix:
def print1dmatrix(matrix1d):
    print()
    for item in matrix1d:
        print(item)

# This function prints a 2D matrix
def print2dmatrix(matrix2d):
    print()
    for row in matrix2d:
        print(row)
    print()

# This funciton prints a 3D matrix
def print3dmatrix(matrix3d):
    print()
    for matrix in matrix3d:
        for row in matrix:
            print(row)
        print()
    print()

# This function convolves a matrix with a smaller matrix
def convolution(image,filter_size):
    # Image : 2d matrix image
    # Column length : length of 2d matrix image
    # filter_size : side length of square filter
    # DECLARE LOCAL VARIABLES
    a = 0                   # Slice [a:b]
    b = filter_size         # Length of filter
    r = 0                   # print row
    r_at = 0                # Number of steps/2
    s = r + filter_size     # Size of filter
    matrix2d = []
    result = []
    column_length = len(image[0])

    # For row in image:
    for row in image:
        # While there is still room to slice horizontally and vertically:
        while b <= (len(row)) and s <= column_length:
            # While there is still room to slice vertically:
            while (r-r_at) < filter_size:
                # Add the row to matrix2d
                matrix2d.append(image[r][a:b])
                r += 1  # Go on to next row
            # increment a,b
            a += 1
            b += 1
            r = r_at
            copy = matrix2d.copy()  # Make copy of 2D Matrix
            result.append(copy) # Add 2d matrix to stacked matrices
            matrix2d.clear()
        r += 1
        r_at += 1
        s += 1
        a = 0   # Slice [a:b]
        b = filter_size
    return result

# This function creates a blank 2D matrix
def create2dmatrix(length, width):
    mat1d = []      # Length
    mat2d = []      # 2D matrix
    for i in range(length):     # Enter 0s
        mat1d.append(0)
    for i in range(width):      # Copy row of 0s
        mat2d.append(mat1d)
    return mat2d

def conv_average(stacked_matrices, mul_filter, size_of_filter):
    # stacked_matrices : pile of convoluted matrices
    # mul_filter : desired filter
    # size_of_filter : dimentsions of filter
    convolved = []
    avg_list = []
    sum_ = 0
    for matrix in stacked_matrices:                                 # for each matrix in stacked matrices
        grid = create2dmatrix(3,3)        # Create a blank matrix
        temp_mat = matrixmult(matrix,mul_filter) # multiply matrix with filter
        convolved.append(temp_mat)
        for row in temp_mat:                                       # compute average of elements
            for item in row:
                sum_ += item
        avg = sum_/(size_of_filter)**2
        avg_list.append(avg)                                        # add average to list of averages
        sum_ = 0
    return convolved, avg_list              # Return multiplied matrices, list of averages

# This function returns the average array as a 2D matrix
def matrix_avg(array,filter_size,image_size):
    length = (image_size - filter_size + 1)
    a = 0
    b = length
    row_value = []      # Items in row
    final = []          # Final array
    while b <= len(array):
        row_value.append(array[a:b])
        a += length
        b += length
    return row_value

# Rectifier unit: converts all negative to 0
def relu(matrix):
    row = 0
    position = 0
    for row_ in matrix:
        position = 0
        for value in row_:
            if value < 0:
                matrix[row][position] = 0
            position += 1
        row += 1
    return matrix

# This function subdivises a larger matrix into smaller matrices
def pooling(image,filter_size):
    # Image : 2d matrix image
    # Column length : length of 2d matrix image
    # filter_size : side length of square filter
    # DECLARE LOCAL VARIABLES
    a = 0                   # Slice [a:b]
    b = filter_size         # Length of filter
    r = 0                   # print row
    r_at = 0                # Number of steps/2
    s = r + filter_size     # Size of filter
    matrix2d = []
    result = []
    column_length = len(image[0])

    # For row in image:
    for row in image:
        # While there is still room to slice horizontally and vertically:
        while b <= (len(row)) and s <= column_length:
            # While there is still room to slice vertically:
            while (r-r_at) < filter_size:
                # Add the row to matrix2d
                matrix2d.append(image[r][a:b])
                r += 1  # Go on to next row
            # increment a,b
            a += filter_size
            b += filter_size
            r = r_at
            copy = matrix2d.copy()  # Make copy of 2D Matrix
            result.append(copy) # Add 2d matrix to stacked matrices
            matrix2d.clear()
        r += filter_size
        r_at += filter_size
        s += filter_size
        a = 0   # Slice [a:b]
        b = filter_size
    return result

# Returns max value for each matrix
def maxPooling(stacked_matrices):
    max_list = []       # List with maximum values
    threshold = 0
    # For every matrix in stacked matrices
    for matrix in stacked_matrices:
        # for every row in matrix:
        for row in matrix:
            # for every value in matrix:
            for value in row:
                if value > threshold:
                    threshold = value
        max_list.append(threshold)
        threshold = 0
    return max_list


# This function adds empty 0s if image is not divisible by filter length
def fill0s(image,filter_size):
    temporary_blank = []
    columns_remaining = filter_size-(len(image[0])%filter_size)
    rows_remaining = filter_size-(len(image)%filter_size)
    if len(image[0])%filter_size != 0:
        for row in image:
            for iteration in range(columns_remaining):
                row.append(0)
        for i in range(rows_remaining):
            for zero in range(len(image[0])):
                temporary_blank.append(0)
            image.append(temporary_blank)
    return image

# This function returns the pooling 1D list as a 2D matrix
def pooling2dReturn(array,filter_size,image_size):
    length = int(image_size/filter_size)
    if image_size % filter_size != 0:
        length += 1
    a = 0
    b = length
    row_value = []      # Items in row
    final = []          # Final array
    while b <= len(array):
        row_value.append(array[a:b])
        a += length
        b += length
    return row_value
