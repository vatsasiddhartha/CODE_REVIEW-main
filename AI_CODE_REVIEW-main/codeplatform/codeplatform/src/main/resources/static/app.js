// Function to show/hide sections (if you have multiple sections on a single page)
function showSection(sectionId) {
    document.querySelectorAll('.section').forEach(sec => sec.style.display = 'none');
    document.getElementById(sectionId).style.display = 'block';
}

// Function to run code via backend
function runCode() {
    const code = document.getElementById('code').value; // Assuming 'code' textarea ID
    const language = document.getElementById('language').value; // Assuming 'language' select ID
    const input = document.getElementById('input').value; // Assuming 'input' textarea ID

    fetch('/run', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({code: code, language: language, input: input})
    })
        .then(response => {
            if (!response.ok) {
                // Handle HTTP errors, e.g., 500 status
                return response.text().then(text => {
                    throw new Error(`HTTP error! status: ${response.status}, message: ${text}`);
                });
            }
            return response.text(); // Backend /run returns plain text
        })
        .then(data => {
            const outputElement = document.getElementById('output'); // Assuming 'output' pre/code block ID
            if (outputElement) {
                outputElement.className = `language-${language}`; // set class dynamically for Prism
                outputElement.textContent = data; // Use textContent to prevent HTML injection
                Prism.highlightAll(); // call highlighting
            } else {
                console.error("Output element not found.");
            }
        })
        .catch(error => {
            console.error('Error running code:', error);
            alert('Error running code: ' + error.message);
        });
}

// Function to request code review from backend and redirect
// ... (other functions) ...

function reviewCode() {
    const code = document.getElementById('code').value;
    const language = document.getElementById('language').value;

    fetch('/review', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ code, language })
    })
        .then(response => {
            if (!response.ok) {
                return response.text().then(text => { throw new Error(`HTTP error! status: ${response.status}, message: ${text}`); });
            }
            return response.json(); // EXPECTING JSON: {"output": "..."}
        })
        .then(jsonData => {
            if (jsonData && typeof jsonData.output === 'string') {
                // Store the ENTIRE JSON object (or just jsonData.output) in sessionStorage
                // sessionStorage is cleared when the browser tab is closed.
                sessionStorage.setItem('codeReviewContent', JSON.stringify(jsonData)); // Store the whole {"output": "..."} object

                // Now, redirect without a large query parameter
                window.open('review.html', '_blank'); // Just open the page, no query param
            } else {
                console.error('Unexpected review response format from /review:', jsonData);
                alert('Received review in unexpected format from server. Check console for details.');
            }
        })
        .catch(error => {
            console.error('Error during code review request:', error);
            alert('Review request failed! Details: ' + error.message);
        });
}
// Function to save code (ensure correct endpoint and element IDs if different)
function saveCode() {
    // Assuming 'codeArea' and 'inputArea' are from a different part of your UI
    // If these are the same as 'code' and 'input' in runCode/reviewCode, adjust IDs.
    const code = document.getElementById('codeArea') ? document.getElementById('codeArea').value : '';
    const language = document.getElementById('language') ? document.getElementById('language').value : ''; // Assuming language is still available
    // The backend saveCode method takes code and language, not code and input
    // const input = document.getElementById('inputArea') ? document.getElementById('inputArea').value : '';

    fetch('/save', { // Updated to /save based on CodeController
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({code, language}) // Send language for saveCode as well
    })
        .then(response => {
            if (!response.ok) {
                return response.text().then(text => {
                    throw new Error(`HTTP error! status: ${response.status}, message: ${text}`);
                });
            }
            return response.json(); // Backend /save returns CodeResponse, which is JSON
        })
        .then(data => { // data will be like { "output": "Code saved successfully..." }
            if (data && typeof data.output === 'string') {
                alert(data.output);
            } else {
                alert('Code save response in unexpected format.');
            }
        })
        .catch(error => {
            console.error('Error saving code:', error);
            alert('Error saving code: ' + error.message);
        });
}