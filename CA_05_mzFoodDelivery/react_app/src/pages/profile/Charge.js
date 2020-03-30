import * as React from "react";

import "../../Assets/styles/profile-style.css";


export  default  class Charge extends React.Component{


    constructor(props) {
        super(props);
        this.state = {
        user: {}
        }
    }


    render() {
        return (<div className="tab1 charge-form">
            <form action="tmp">
                <div className="row justify-content-center align-items-center">
                    <div className="col-8 my-2">
                        <div>
                            <input type="text" placeholder="میزان افزایش اعتبار" />
                        </div>
                    </div>
                    <div className="col-4 my-2">
                        <div>
                            <input type="submit" value="افزایش" />
                        </div>
                    </div>
                </div>
            </form>
        </div>);
    }

}