const axios = require("axios").default;

module.exports = {
  getRestaurants: function() {
    return axios.get("http://localhost:8080/restaurants");
  }
};
