import React from "react";
import {animationStyles} from "../../config/configStyle";
import {toast} from "react-toastify";
import {TOAST_MESSAGE_FILLED_BOTH_SEARCH, TOAST_MESSAGE_EMPTY_SEARCH} from "../../config/config";
import {homeRestaurantsRefresh} from "../../services/subjects/MessageService";
import RestaurantService from "../../services/RestaurantService";

export default class HomeSearchBar extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            foodInputText: "",
            restaurantInputText: "",
            isLoading: false
        };
    }

    componentDidMount() {
        toast.configure({rtl: true, className: "text-center"});
    }

    updateFoodInputText = function (event) {
        this.setState({
            foodInputText: event.target.value
        });
    };

    updateRestaurantInputText = function (event) {
        this.setState({
            restaurantInputText: event.target.value
        });
    };

    submitSearch(e) {
        e.preventDefault();
        let restaurantText = this.state.restaurantInputText;
        let foodText = this.state.foodInputText;
        console.log(restaurantText, foodText);

        if (restaurantText === "" && foodText === "") {
            toast.error(TOAST_MESSAGE_EMPTY_SEARCH);
            this.resetHomeRestaurants();
        } else if (restaurantText !== "" && foodText !== "") {
            toast.warn(TOAST_MESSAGE_FILLED_BOTH_SEARCH);
            this.searchRestaurants(restaurantText);
        } else if (restaurantText !== "") {
            this.searchRestaurants(restaurantText);
        } else if (foodText !== "") {
            this.searchFoods(foodText);
        }
    }

    resetHomeRestaurants() {
        homeRestaurantsRefresh.next([RestaurantService.getRestaurants, null]);
    }

    searchFoods(searchPhrase) {
        homeRestaurantsRefresh.next([RestaurantService.searchFoods, searchPhrase]);
    }

    searchRestaurants(searchPhrase) {
        homeRestaurantsRefresh.next([RestaurantService.searchRestaurants, searchPhrase]);
    }

    render() {
        return (
            <div className="search-bar" style={animationStyles.bounce}>
                <div className="outer-box">
                    <form
                        action=""
                        className="w-100 m-0 d-flex justify-content-center align-items-center">
                        <div className="row w-100">
                            <div className="food-name col">
                                <input
                                    className="search-item"
                                    placeholder="نام غذا"
                                    value={this.state.foodInputText}
                                    onChange={evt => this.updateFoodInputText(evt)}
                                />
                            </div>
                            <div className="restaurant-name col">
                                <input
                                    className="search-item"
                                    placeholder="نام رستوران"
                                    value={this.state.restaurantInputText}
                                    onChange={evt => this.updateRestaurantInputText(evt)}
                                />
                            </div>
                            <div className="search-btn col">
                                <button className="search-item"
                                        onClick={this.submitSearch.bind(this)}>
                                    جست‌و‌جو
                                    {this.state.isLoading &&
                                    <span className="spinner-border mr-2" role="status" aria-hidden="true"/>
                                    }
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        );
    }
}