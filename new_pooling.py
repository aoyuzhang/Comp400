"""
This program attempts to perform pooling on a 2D MATRIX, by compressing it into a 1D array
The following is written in python code.
The Java version is a lot better maintained and more practical and convenient.
"""
import random


image_rand = [[None]*5 for i in range(5)]
for i in range(len(image_rand)):
    for j in range(len(image_rand[0])):
        image_rand[i][j] = random.randint(0,10)

def print2dmatrix(matrix2d):
    """This function prints a 2D matrix"""
    print()
    for row in matrix2d:
        print(row)
    print()

def fill0s(image,filter_size):
    """Fills 0s.
    image: original 2x2 matrix
    filter_size: filter size"""
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

def convert1d(image):
    """Convert 1D image"""
    arrayFinal = [None]*(len(image)*len(image[0]))
    counter = 0

    for i in range(len(image)):
        for j in range(len(image[0])):
            arrayFinal[counter] = image[i][j]
            counter += 1

    return arrayFinal

# generate indices
def generate_indices(filter_size,image_array,image):
    """This function generates a list of indices that the next function uses to access the picture"""
    width_of_image = len(image[0])
    height_of_image = len(image)
    counter_list = [None]*int(((width_of_image/filter_size)*height_of_image))
    for i in range(len(counter_list)):
        counter_list[i] = i
    return counter_list

def sort_indices(unsorted_indices, filter_size, image, image_array):
    """Reorder indices in a way that is suitable for indexing the image"""
    print()
    sorted_indices = [None]*int(len(unsorted_indices))
    counter = 0
    biais = 0
    add_horizontal = 0    # Adds to index position every time position moves horizontally
    # moving vertically:
    for i in range(int(len(image)/filter_size)):
        # moving horizontally
        for j in range(int(len(image[0])/filter_size)):
            # for each item in row:
            for k in range(filter_size):
                # k being the index of item in filter
                sorted_indices[counter] = unsorted_indices[int((len(image[0])/filter_size)*k + biais)]
                #print(sorted_indices)
                counter += 1
            biais += 1
            add_horizontal += 1
        biais -= add_horizontal
        biais += int(len(image[0]))
        add_horizontal = 0
    return sorted_indices

def nest_image_array(image_array, filter_size):
    """Converts 1D array into 2D matrix"""
    # Matrix
    nested_image_array = [[None]*(filter_size) for i in range(int(len(image_array)/filter_size))]
    counter = 0
    for number in range(int(len(image_array)/filter_size)):
        for iterator in range(filter_size):
            nested_image_array[number][iterator] = image_array[counter]
            counter += 1
    return nested_image_array

def iterate_indices(image_array,nested_image_array,sorted_indices,filter_size):
    """Returns a usable 1D representation of the image"""

    sorted_image_array = [None]*len(image_array)
    counter = 0


    # For each nested image:
    for i in range(int(len(image_array)/filter_size)):
        # For each item in nested image:
        for item in range(filter_size):
            sorted_image_array[counter] = nested_image_array[sorted_indices[i]][item]
            counter += 1

    return sorted_image_array

def return_maximum(image_array, filter_size):
    """This function returns the maximum of each nested list"""
    maximums_array = [None]*(int(len(image_array)/(filter_size*filter_size))) # list used to store maximums
    temp_list = [None]*(filter_size*filter_size)
    counter = 0
    index = 0
    max_value = 0
    for number in image_array:        # for number of maxumums to be returned
        counter += 1
        if counter%(filter_size*filter_size) != 0 and number > max_value:       # if not yet equal to filter size and higher than max value:
            max_value = number
        if counter%(filter_size*filter_size) == 0:    # once at the end of the filter
            if number > max_value:
                max_value = number
            maximums_array[index] = max_value   # append maximum value to the list
            max_value = 0
            index += 1
    return maximums_array

def restore_format(max_list,filter_size,image_height,image_width):
    """This function reconverts the 1D array into a 2D matrix"""
    pooled_height = int(image_height/filter_size)
    pooled_width = int(image_width/filter_size)
    temp_row = [None]*pooled_width
    image = []
    counter = 0
    for i in range(pooled_height):          # for each row:
        for j in range(pooled_width):       # for each column:
            temp_row[j] = max_list[counter]
            counter += 1
        image.append(temp_row)
        temp_row = [None]*pooled_width
    return image

if __name__ == "__main__":
    print('ORIGINAL IMAGE:')
    image = fill0s(image_rand,2)
    print2dmatrix(image)
    temp_list = convert1d(image)
    print('1D IMAGE: {}'.format(temp_list))
    unsorted_indices = generate_indices(2,temp_list,image)
    print('INDICES: {}'.format(unsorted_indices))
    sorted_indices = sort_indices(unsorted_indices,2,image,temp_list)
    print('SORTED INDICES: {}'.format(sorted_indices))
    nested_image = nest_image_array(temp_list,2)
    print('NESTED IMAGE: {}'.format(nested_image))
    sorted_image_array = iterate_indices(temp_list,nested_image,sorted_indices,2)
    print('SORTED IMAGE: {}'.format(sorted_image_array))
    max_list = return_maximum(sorted_image_array, 2)
    print('MAXIMUMS LIST: {}'.format(max_list))
    pooled_image = restore_format(max_list,2,6,6)
    print('FINAL IMAGE:')
    print2dmatrix(pooled_image)
