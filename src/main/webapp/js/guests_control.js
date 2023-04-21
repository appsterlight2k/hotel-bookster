    function SubmitSearch() {
        const search = document.getElementById('button-search');
        search.click();
    }

    let range = document.getElementById('range');
    let rangeValue = document.getElementById("rangeValue");

    rangeValue.innerHTML = "Guests number: " + range.value;

    range.addEventListener("input", function() {
        range.setAttribute('value', range.value);
        rangeValue.innerHTML = "Guests number: " + range.value;
    });

    range.addEventListener("change", function() {
        SubmitSearch();
    });

    function getRange() {
        return range.value;
    }
