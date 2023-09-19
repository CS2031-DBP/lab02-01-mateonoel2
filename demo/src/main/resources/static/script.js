document.addEventListener('DOMContentLoaded', function() {
    fetch('/song')
    .then(response => response.json())
    .then(data => {
        const table = document.createElement('table');
        const headerRow = document.createElement('tr');
        
        // Create table headers
        ['ID', 'Title', 'Artist', 'Album', 'Release Date', 'Genre', 'Duration', 'Cover Image', 'Spotify URL', 'Actions'].forEach(text => {
            const th = document.createElement('th');
            th.appendChild(document.createTextNode(text));
            headerRow.appendChild(th);
        });

        table.appendChild(headerRow);
        
        // Create table rows
        data.forEach(song => {
            const row = document.createElement('tr');
            
            [song.id, song.title, song.artist, song.album, song.releaseDate, song.genre, song.duration, song.coverImage, song.spotifyUrl].forEach(text => {
                const td = document.createElement('td');
                td.appendChild(document.createTextNode(text));
                row.appendChild(td);
            });
            
            table.appendChild(row);
        });

        // Create an empty row at the end for adding a new song
        const emptyRow = document.createElement('tr');
        const inputNames = ['', 'title', 'artist', 'album', 'releaseDate', 'genre', 'duration', 'coverImage', 'spotifyUrl'];
        
        inputNames.forEach(name => {
            if (name === '') {
                const td = document.createElement('td');
                td.appendChild(document.createTextNode('New Song'));
                emptyRow.appendChild(td);
            }
            else{
                const td = document.createElement('td');
                const input = document.createElement('input');
                input.name = name;
                input.type = 'text';
                td.appendChild(input);
                emptyRow.appendChild(td);
            }
        });

        // Add a submit button
        const actionTd = document.createElement('td');
        const submitButton = document.createElement('button');
        submitButton.textContent = 'Add Song';
        submitButton.addEventListener('click', addNewSong);
        actionTd.appendChild(submitButton);
        emptyRow.appendChild(actionTd);

        table.appendChild(emptyRow);
        
        document.body.appendChild(table);
    });
});

// Function to add a new song
function addNewSong() {
    const newSong = {};
    const inputNames = ['title', 'artist', 'album', 'releaseDate', 'genre', 'duration', 'coverImage', 'spotifyUrl'];
    
    inputNames.forEach(name => {
        const input = document.querySelector(`input[name="${name}"]`);
        newSong[name] = input.value;
    });

    // Send POST request to add a new song
    fetch('/song', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newSong)
    })
    .then(response => response.json())
    .then(data => {
        if(data.status === 201) {
            alert('New song added successfully');
            location.reload(); // Reload the page to see the new song
        } else {
            alert('Failed to add new song');
        }
    });
}
