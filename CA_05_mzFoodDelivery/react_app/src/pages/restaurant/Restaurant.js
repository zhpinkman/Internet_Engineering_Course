import React from "react";
import Header from "../general/Header";
import Footer from "../general/Footer";
import "../../Assets/styles/restaurant-style.css";
import {FoodPartyService} from "../../services/FoodPartyService";
import RestaurantService from "../../services/RestaurantService";
import FoodPartyCard from "../home/foodParty/foodPartyCard/FoodPartyCard";
import RestaurantMenuCard from "./RestaurantMenuCard";
import Cart from "../general/modals/Cart";

export default class Restaurant extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            queryParams: new URLSearchParams(window.location.search),
            restaurantId: new URLSearchParams(window.location.search).get("id"),
            restaurant: null,
            notFound: false
        };

        if (this.state.restaurantId == null) {
            this.props.history.push("/");
        } else {
            this.getRestaurantById(this.state.restaurantId);
        }

    }



    componentDidMount() {
        // window.scrollTo(0, 0);
        const scrollToTop = () => {
            const c = document.documentElement.scrollTop || document.body.scrollTop;
            if (c > 0) {
                window.requestAnimationFrame(scrollToTop);
                window.scrollTo(0, c - c / 8);
            }
        };
        scrollToTop();
    }

    async getRestaurantById(id) {
        let restaurant = await RestaurantService.getRestaurantById(id);
        if (restaurant == null) {
            this.setState({
                notFound: true
            });
        } else {
            this.setState({
                restaurant: restaurant
            });
        }
    }

    render() {
        return (
            <div className="h-100">
                <Header/>
                {this.state.restaurant !== null ?
                    this.renderMain()
                    :
                    this.renderLoading()
                }
                <Footer/>
            </div>
        );
    }

    renderMenu() {
        return this.state.restaurant.menu.map((foodItem, i) =>
            <RestaurantMenuCard food={this.createFoodItem(foodItem)} key={"FOOD" + i}/>
        );
    }

    createFoodItem(foodItem) {
        foodItem.restaurantId = this.state.restaurantId;
        foodItem.restaurantName = this.state.restaurant.name;
        return foodItem;
    }

    renderLoading() {
        return (
            <div className="h-100 m-5 center-text p-5">
                <div className="spinner-grow text-danger m-5" role="status">
                    <span className="sr-only">Loading...</span>
                </div>
            </div>
        );
    }

    renderMain() {
        return (
            <main>
                <div className="sub-header-red">
                    <div className="sub-header-over">
                        <img alt="tmp" className="sub-header-over-img"
                             src={this.state.restaurant.logo}/>
                    </div>
                </div>
                <div className="sub-header-over-text">{this.state.restaurant.name}</div>

                <div className="container restaurant-main-container">

                    <div className="row">
                        <div className="col-lg-4 col-6 center-text"></div>
                        <div className="col-lg-8 col-6 center-text">
                    <span className="menu-text">
                        منوی غذا
                    </span>
                        </div>
                    </div>
                    <div className="row mt-5">

                        <div className="col-lg-4 col-6">
                            <Cart/>
                        </div>

                        <div className="col-lg-8 col-6 food-list-container">
                            <div className="row">


                                {this.renderMenu()}

                            </div>
                        </div>
                    </div>
                </div>

            </main>
        );
    }
}