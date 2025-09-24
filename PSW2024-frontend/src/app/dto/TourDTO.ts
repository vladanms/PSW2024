import { ComplaintDTO } from "./complaintDTO";
import { KeyPointDTO } from "./KeyPointDTO";
import { GradeDTO } from "./GradeDTO";

export class TourDTO {
  id !: number;
  name !: string;
  description !: string;
  category !: string;
  difficulty !: number;
  price !: number;
  time !: string;
  guide !: string;
  tourists !: string[];
  keyPoints !: KeyPointDTO[];
  complaints !: ComplaintDTO[];
  isPublished !: boolean;
  grades  !: GradeDTO[];
}