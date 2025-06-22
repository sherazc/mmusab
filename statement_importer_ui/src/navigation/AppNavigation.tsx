import React from "react";
import { Link } from "react-router";

interface Props {}

export const AppNavigation:React.FC<Props> = () => {

  return (
    <nav>
      <Link to='/'>Home</Link>&nbsp;|&nbsp;
      <Link to='/login'>Login</Link>&nbsp;|&nbsp;
    </nav>
  );
}
