export interface Token {
  access_token : string;
  refresh_token :string

  role:string;
}

export interface DecodedToken {
  sub: string;
  iat:number;
  exp:number;
}
