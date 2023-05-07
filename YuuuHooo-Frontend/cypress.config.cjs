const { defineConfig } = require("cypress");

module.exports = defineConfig({
  e2e: {
   specPattern: "cypress/e2e/**/*.{cy,spec}.{js,jsx,ts,tsx}",
  //  baseUrl:"http://ip22nw2.sit.kmutt.ac.th:80/",
   baseUrl:"http://localhost:5173/",
   experimentalSessionAndOrigin: true
  },
});
