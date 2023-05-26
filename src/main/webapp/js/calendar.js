
    let calendar = flatpickr("#datepicker", {
        dateFormat: "Y-m-d",
        locale: "en",
        theme: "material_blue",
        disableMobile: true,
        onChange: function(selectedDates, dateStr, instance) {
            let isMultidate = true;
            const multiDateMode = document.getElementById("multiDateMode");

            if (typeof multiDateMode !== 'undefined' && multiDateMode !== null) {
                isMultidate = (multiDateMode.value === "1");
            }

            if (selectedDates.length === 1) {
                let startDate = selectedDates[0];
                let startDateInput = document.getElementById("startDate");
                startDateInput.value = parseDateInputValue(startDate);
                if (!isMultidate) submitSearch();
            } else if (selectedDates.length === 2) {
                let endDate = selectedDates[1];
                let endDateInput = document.getElementById("endDate");
                endDateInput.value = parseDateInputValue(endDate);
                submitSearch();
            }
        }
    });

    function parseDateInputValue(date) {
        return date.getFullYear() + '-' +
        (date.getMonth() + 1).toString().padStart(2, '0') +
        '-' + date.getDate().toString().padStart(2, '0');
    }
