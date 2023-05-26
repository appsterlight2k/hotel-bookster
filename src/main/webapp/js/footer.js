    function submitSearch() {
        action.value = config.baseAction;
        const form = document.getElementById('form-search');
        form.submit();
    }

    doChangePages();

    function setActivePage(pageId, isFooter) {
        const pagesSelect = isFooter
            ? document.querySelectorAll(".footer-page-bar li.page-item a")
            : document.querySelectorAll("#modal-pagination li.page-item a");

        pagesSelect.forEach(page => {
            page.parentNode.classList.remove("active"); //remove class "active"

            if (page.innerText === pageId) {
                page.parentNode.classList.add("active"); //add class "active" to chosen li
            }
        });
    }

    function doChangePages() {
        let liElements =
            document.querySelectorAll('.footer-page-bar li.page-item, #modal-pagination li.page-item');

        liElements.forEach(function (liElement) {
            liElement.addEventListener('click', function (event) {
                const select = event.target;
                const container = select.closest('.footer-page-bar, #modal-pagination');
                const isFooter = container.classList.contains('footer-page-bar');

                const pagination = document.querySelector('.pagination');
                const numberOfPages = pagination.children.length - 2;
                const pagesCount = isFooter ? parseInt(config.pagesCount) : numberOfPages;

                const page = isFooter
                    ? document.querySelector('#page')
                    : document.querySelector('#modal-page');

                const pageValue = parseInt(page.value);

                const submitFunction = isFooter ? submitSearch : submitAjaxSearch;

                const linkId = this.querySelector('a').id;
                switch(linkId) {
                    case 'prev-btn':
                        if (pageValue !== 1) {
                            page.value--;
                            if (!isFooter) setActivePage(page.value, isFooter);
                            submitFunction();
                        }
                        break;
                    case 'next-btn':
                        if (pageValue !== pagesCount) {
                            page.value++;
                            if (!isFooter) setActivePage(page.value, isFooter);
                            submitFunction();
                        }
                        break;
                    default:
                        page.value = this.innerText;
                        if (!isFooter) setActivePage(page.value, isFooter);
                        submitFunction();
                }
            });
        });
    }

    const footerPageSize = document.querySelector(".footer-page-bar select.form-select");
    footerPageSize.name = 'pageSize';
    const modalPageSize = document.querySelector("#modal-pagination select.form-select");
    if (modalPageSize !== undefined && modalPageSize !==null) {
        modalPageSize.name = 'modalPageSize';
    }

    const selects =
        document.querySelectorAll('.footer-page-bar select.form-select, #modal-pagination select.form-select');
    selects.forEach((select) => {
        select.addEventListener('change', onChangePageSize);
    });

    function onChangePageSize(event) {
        const select = event.target;
        const container = select.closest('.footer-page-bar, #modal-pagination');
        const isFooter = container.classList.contains('footer-page-bar');

        const pageSizeSelect = isFooter
            ? document.querySelector(".footer-page-bar select.form-select")
            : document.querySelector("#modal-pagination select.form-select");

        let options = Array.from(pageSizeSelect.options);
        options.forEach(option => {
            option.removeAttribute('selected');
        });

        let selectedOption = options[pageSizeSelect.selectedIndex];
        selectedOption.setAttribute("selected", "");

        const page = isFooter
            ? document.querySelector('#page')
            : document.querySelector('#modal-page');

        page.value = 1;

        const submitFunction = isFooter ? submitSearch : submitAjaxSearch;
        submitFunction();
    }

    function generatePageButtons(response) {
        const paginationContainer = document.querySelector('#modal-pagination .pagination');
        const totalPages = response.pagesCount > 0 ? response.pagesCount : 1;
        let currentPage = response.page;

        //Clear container
        paginationContainer.innerHTML = '';

        //Add button "Previous"
        const previousButton = document.createElement('li');
        previousButton.classList.add('page-item');
        previousButton.innerHTML = '<a id="prev-btn" class="page-link" href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>';
        paginationContainer.appendChild(previousButton);

        for (let i = 1; i <= totalPages; i++) {
            const pageButton = document.createElement('li');
            const pageButtonLink = document.createElement('a');

            pageButton.classList.add('page-item');
            pageButtonLink.classList.add('page-link');

            if (i === currentPage) {
                pageButton.classList.add('active');
                pageButton.setAttribute('aria-current', 'page');
            }

            pageButtonLink.href = '#';
            pageButtonLink.innerText = i;

            pageButton.appendChild(pageButtonLink);
            paginationContainer.appendChild(pageButton);
        }

        //Add button "Next"
        const nextButton = document.createElement('li');
        nextButton.classList.add('page-item');
        nextButton.innerHTML = '<a id="next-btn" class="page-link" href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a>';
        paginationContainer.appendChild(nextButton);

        //get all buttons for pages:
        const pageButtons = document.querySelectorAll('#modal-pagination .pagination-bar .page-item:not(.disabled) .page-link');

        //add event listener of event 'click' for all buttons
        pageButtons.forEach(function(button) {
            button.addEventListener('click', function(event) {
                event.preventDefault();
                doChangePages();
            });
        });
    }