
    window.addEventListener("DOMContentLoaded", (event) => {
        setDateControlsState();
    });

    function SubmitSearch() {
        const search = document.getElementById('button-search');
        search.click();
    }

    function setDateControlsState() {
        const flexByPeriod = document.getElementById('flexSwitchByPeriod');
        const multiDate = document.getElementById('multiDateMode');
        const datepicker = document.getElementById('datepicker');

        multiDate.disabled = !flexByPeriod.checked;
        datepicker.disabled = !flexByPeriod.checked;
    }

    function onChangeSwitch() {
        setDateControlsState();
        SubmitSearch();
    }


    function onChangeMultiDateMode() {
        let multiDateMode = document.getElementById("multiDateMode");
        setMultiDateCalendarMode(multiDateMode.value);
        action.value = config.baseAction;
        SubmitSearch();
    }

    function setMultiDateCalendarMode(isMultiDate) {
        let startDateField = document.getElementById("startDate");
        let endDateField = document.getElementById("endDate");
        let selectedDates = calendar.selectedDates;

        calendar.clear();

        if (isMultiDate === "0") {
            let startDate = selectedDates[0];
            startDateField.value = parseDateInputValue(startDate);
            endDateField.value = null;
            calendar.set("placeholder", "Select date");
            calendar.set("mode", "single");
            calendar.setDate(startDate);
        } else {
            let startDate = selectedDates[0];
            startDateField.value = parseDateInputValue(startDate);
            endDateField.value = startDateField.value;
            calendar.set("mode", "range");
            calendar.set("placeholder", "Select date range");
            calendar.setDate([startDate, startDate]);
        }
    }