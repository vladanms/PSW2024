export interface ComplaintDTO {
  id: number;
  user: { username: string;
  };
  tourId?: number;
  name: string;
  description: string;
  status: string;
}