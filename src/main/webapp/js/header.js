function setActiveTab(index) {
    const navLinks = document.querySelectorAll('.nav-link');
    navLinks.forEach((link) => {
        link.classList.remove('active');
    });

    const targetLink = document.querySelector(`.nav-item:nth-child(${index + 1}) .nav-link`);
    if (targetLink) {
        targetLink.classList.add('active');
    }
}