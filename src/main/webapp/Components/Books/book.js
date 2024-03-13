

// Function to fetch books from the server
function fetchBooks() {
    $.ajax({
        url: 'http://localhost:8080/prod/api/book/all',
        method: 'GET',
        dataType: 'json',
        success: function (data) {
            displayBooks(data);
        },
        error: function (xhr, status, error) {
            console.error('Error fetching Books:', error);
        }
    });
}

fetchBooks();

// Function to delete a book by ID
function deleteBook(bookID) {
    // Open the confirmation modal
    $('#confirmationModal').modal('show');

    // Handle click on the "Delete" button inside the modal
    $('#confirmDeleteBtn').off('click').on('click', function () {
        // Close the modal
        $('#confirmationModal').modal('hide');

        // Perform the actual deletion
        $.ajax({
            url: 'http://localhost:8080/prod/api/book/' + bookID,
            method: 'DELETE',
            success: function (data) {
                // Fetch and display updated books data
                fetchBooks();
            },
            error: function (xhr, status, error) {
                            if (xhr.status === 500) {
                                console.error('Error deleting Klant:', error);
                                alert('This Klant is referenced in another table and cannot be deleted.');
                            } else {
                                console.error('Unexpected error:', error);
                            }
                        }
        });
    });
}

// Function to edit a book by ID
function editBook(bookID) {
    // Fetch the existing book information
    $.ajax({
        url: 'http://localhost:8080/prod/api/book/' + bookID,
        method: 'GET',
        dataType: 'json',
        success: function (book) {
            // Populate the form fields with existing information
            $('#editBookID').val(book.bookID);
            $('#editTitle').val(book.title);
            $('#editAuthor').val(book.author);
            $('#editGenre').val(book.genre);
            $('#editDescription').val(book.description);
            $('#editPrice').val(book.price);
            $('#editStockQuantity').val(book.stockQuantity);
            $('#editPublicationDate').val(book.publicationDate);
            $('#editIsbnNumber').val(book.isbnNumber);
            $('#editCopy').val(book.copy);
            // Add other fields as needed

            // Display the edit modal
            $('#editBookModal').modal('show');

            // Set up a click event for the "Save Changes" button in the modal
            $('#saveChangesBtn').off('click').on('click', function () {
                // Call the function to save the changes
                saveBookChanges(book.bookID);
            });
        },
        error: function (xhr, status, error) {
            console.error('Error fetching Book for edit:', error);
            // Display an error message on the page if needed
        }
    });
}


// Function to save changes to a book
//

function saveBookChanges() {
    var updatedData = {
        bookID: $('#editBookID').val(),
        title: $('#editTitle').val(),
        author: $('#editAuthor').val(),
        genre: $('#editGenre').val(),
        description: $('#editDescription').val(),
        price: $('#editPrice').val(),
        stockQuantity: $('#editStockQuantity').val(),
        publicationDate: $('#editPublicationDate').val(),
        isbnNumber: $('#editIsbnNumber').val(),
        copy: $('#editCopy').val()
        // Add other fields as needed
    };

    $.ajax({
        url: 'http://localhost:8080/prod/api/book/update/' + $('#editBookID').val(),
        method: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(updatedData),
        success: function (data) {
            fetchBooks();
            $('#editBookModal').modal('hide');
        },
        error: function (xhr, status, error) {
            console.error('Error updating Book:', error);
        }
    });
}


// Function to display books on the page
function displayBooks(books) {
    var tableBody = $('#booksTableBody');
    tableBody.empty();

    books.forEach(function (book) {
        var row = '<tr>' +
            '<td>' + book.id + '</td>' +
            '<td>' + book.title + '</td>' +
            '<td>' + book.author + '</td>' +
            '<td>' + book.genre + '</td>' +
            '<td>' + book.description + '</td>' +
            '<td>' + book.price + '</td>' +
            '<td>' + book.stockQuantity + '</td>' +
            '<td>' + book.publicationDate + '</td>' +
            '<td>' + book.isbnNumber + '</td>' +
            '<td>' + book.copy + '</td>' +
            // Add other fields as needed
            '<td>' +
            '<button class="btn btn-warning btn-sm" onclick="editBook(' + book.id + ')">Edit</button> ' +
            '<button class="btn btn-danger btn-sm" onclick="deleteBook(' + book.id + ')">Delete</button>' +
            '</td>' +
            '</tr>';

        tableBody.append(row);
    });
}


// Function to save a new book
function saveBook() {
    var newData = {
        title: $('#createTitle').val(),
        author: $('#createAuthor').val(),
        genre: $('#createGenre').val(),
        description: $('#createDescription').val(),
        price: $('#createPrice').val(),
        stockQuantity: $('#createStockQuantity').val(),
        publicationDate: $('#createPublicationDate').val(),
        isbnNumber: $('#createIsbnNumber').val(),
        copy: $('#createCopy').val()
        // Add other fields as needed
    };

    $.ajax({
        url: 'http://localhost:8080/prod/api/book/create',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(newData),
        success: function (data) {
            fetchBooks();
            $('#createBookModal').modal('hide');
        },
        error: function (xhr, status, error) {
            console.error('Error creating Book:', error);
        }
    });
}

