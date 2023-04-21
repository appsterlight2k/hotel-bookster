
    function SubmitSearch() {
        const search = document.getElementById('button-search');
        search.click();
    }

    var liElements = document.querySelectorAll('#pagination-bar li.page-item');

    for (var i = 0; i < liElements.length; i++) {
        liElements[i].addEventListener('click', function() {
            var page = document.getElementById('page');

            if (this.querySelector('span').id === 'prev-btn') {
                if (page.value !== '1') {
                    page.value--;
                    SubmitSearch();
                }
            } else if (this.querySelector('span').id === 'next-btn') {
                if (page.value !== config.pagesCount) {
                    page.value++;
                    SubmitSearch();
                }
            } else {
                page.value = this.innerText;
                SubmitSearch();
            }
        });
    }

    function onChangePageSize() {
        var select = document.getElementById("pageSize");

        var options = select.options;
        for (var i = 0; i < options.length; i++) {
            options[i].removeAttribute("selected");
        }
        var selectedOption = options[select.selectedIndex];
        selectedOption.setAttribute("selected", "");
        selectedOption.value;
        SubmitSearch();
    }
