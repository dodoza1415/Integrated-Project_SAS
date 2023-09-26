const { defineConfig } = require("cypress");

module.exports = defineConfig({
  e2e: {
   specPattern: "cypress/e2e/**/*.{cy,spec}.{js,jsx,ts,tsx}",
   baseUrl:"https://intproj22.sit.kmutt.ac.th/nw2/", 
  //  baseUrl:"http://localhost:5173/",
   experimentalSessionAndOrigin: true
  },
});
