export interface PositionI{
  latitude:number,
  longitude:number
}

export class Position implements PositionI{
  latitude:number = 45.4;
  longitude:number = 19.60;
}

