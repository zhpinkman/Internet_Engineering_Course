// import * as React from "react";
//
// import "../../Assets/styles/profile-style.css";
// import UserService from "../../services/UserService";
//
//
// export  default  class Charge extends React.Component{
//
//
//     constructor(props) {
//         super(props);
//         this.state = {
//         user: {},
//         amount: 0
//         };
//
//
//         this.handleChange = this.handleChange.bind(this);
//         this.handleSubmit = this.handleSubmit.bind(this);
//     }
//
//
//     handleChange(event) {
//         this.setState({amount: event.target.value});
//     }
//     handleSubmit(event) {
//         event.preventDefault();
//         UserService.charge(this.state.amount).then(resp => {
//             console.log(resp);
//         });
//     }
//
//
//     render() {
//         return (<div className="tab1 charge-form">
//             <form onSubmit={this.handleSubmit}>
//                 <div className="row justify-content-center align-items-center">
//                     <div className="col-8 my-2">
//                         <div>
//                             <input type="text" value={this.state.amount}  onChange={this.handleChange}  name="amount" placeholder="میزان افزایش اعتبار" />
//                         </div>
//                     </div>
//                     <div className="col-4 my-2">
//                         <div>
//                             <input type="submit" value="افزایش" />
//                         </div>
//                     </div>
//                 </div>
//             </form>
//         </div>);
//     }
//
// }