import React from "react";
import {animationStyles} from "../../config/configStyle";

export default class HomeSearchBar extends React.Component {
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
                                    placeholder="نام غذا"/>
                            </div>
                            <div className="restaurant-name col">
                                <input className="search-item" placeholder="نام رستوران"/>
                            </div>
                            <div className="search-btn col">
                                <input className="search-item" type="submit" value="جست‌و‌جو"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        );
    }
}